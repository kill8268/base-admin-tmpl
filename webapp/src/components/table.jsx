import { useMemo } from "react";

function ColumnComponent({ type = "text", value, category }) {
  switch (type) {
    case "boolean":
      return (
        <input
          type="checkbox"
          defaultChecked={value}
          readOnly
          className="checkbox checkbox-sm checkbox-primary pointer-events-none"
        />
      );
    case "date":
      return new Date(value).toLocaleString().split(" ")[0];
    case "datetime":
      return new Date(value).toLocaleString();
    case "dist":
      return "";
    default:
      return value;
  }
}

/**
 * Table组件，用于显示表格数据
 *
 * @param {Object} props - 组件的props
 * @param {Array} props.data - 表格数据
 * @param {ReactNode} props.children - 子组件，应为Table.Column组件
 * @param {string} props.className - CSS类名
 * @returns {ReactNode} 返回React节点
 */
function Table({ data, children, className }) {
  const columns = useMemo(() => children.map((item) => item.props), [children]);

  return (
    <table className={className}>
      <thead className="text-sm">
        <tr>{children}</tr>
      </thead>
      <tbody>
        {data.map((item) => (
          <tr className="text-sm" key={item.id}>
            {columns.map((column) => (
              <td key={column.name} className={`${column.tdClassName}`}>
                {column.render ? (
                  column.render(item[column.name], item)
                ) : (
                  <ColumnComponent
                    type={column.type}
                    value={item[column.name]}
                    category={item.category}
                  />
                )}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

/**
 * Column组件，用于显示表格列
 *
 * @param {Object} props - 组件的props
 * @param {string} props.title - 列标题
 * @param {string} props.className - CSS类名
 * @param {string} props.name - 列名
 * @param {string} props.tdClassName - 列CSS类名
 * @param {Function} props.render - 渲染函数
 * @param {string} props.type - 列类型
 * @param {string} props.category - 字典分类
 * @returns {ReactNode} 返回React节点
 */
Table.Column = function Column({ title, className }) {
  return <th className={className || ""}>{title}</th>;
};

export default Table;
