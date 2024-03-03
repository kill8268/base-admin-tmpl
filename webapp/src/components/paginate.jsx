import { useMemo, useState } from "react";
import ReactPaginate from "react-paginate";

/**
 * Paginate组件，用于分页
 *
 * @param {Object} props - 组件的props
 * @param {number} props.total - 总条数
 * @param {number} props.size - 每页显示的条数
 * @param {Function} props.onPageChange - 页码改变时的回调函数
 * @param {string} props.className - CSS类名
 * @returns {ReactNode} 返回React节点
 */
export default function Paginate({
  total = 0,
  size = 0,
  onPageChange,
  className,
}) {
  const [value, setValue] = useState("1");

  const pageCount = useMemo(() => {
    const count = Math.ceil(total / size);
    return isNaN(count) ? 0 : count;
  }, [total, size]);

  const handlePageChange = (page) => {
    onPageChange(page.selected + 1);
  };

  const handleJumpPage = (event) => {
    const inputValue = event.target.value;
    const intValue = parseInt(inputValue, 10);
    if (isNaN(intValue)) {
      setValue("");
    } else {
      setValue(inputValue);
      onPageChange(inputValue);
    }
  };
  return (
    <div className={className}>
      <div className="flex items-center bg-primary text-white rounded-full px-4 h-8">
        共&nbsp;{total}&nbsp;条
      </div>
      <div className="flex space-x-4">
        <ReactPaginate
          className="flex gap-4 items-center"
          breakLabel="..."
          nextLabel=">"
          previousLabel="<"
          nextClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
          onPageChange={handlePageChange}
          pageRangeDisplayed={3}
          pageCount={pageCount}
          activeClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
          pageClassName="font-semibold px-2"
          previousClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
        />
        <div className="flex items-center py-2 h-8 space-x-1 bg-primary text-white font-semibold rounded-full px-4">
          前往
          <input
            value={value}
            onChange={handleJumpPage}
            className="bg-primary w-8 text-center"
          />
          页
        </div>
      </div>
    </div>
  );
}
