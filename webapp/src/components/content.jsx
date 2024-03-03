import Fade from "./fade";
import Table from "./table";
import { useEffect } from "react";
import Paginate from "./paginate";
import useTitle from "@/store/useTitle";
import styles from "@/assets/styles/content.module.less";

/**
 * Content组件，用于显示内容
 *
 * @param {Object} props - 组件的props
 * @param {ReactNode} props.children - 子组件
 * @param {string} props.className - CSS类名
 * @param {string} props.title - 页面标题
 * @param {string} props.dreadcrumb - 面包屑导航
 * @returns {ReactNode} 返回React节点
 */
function Content({ children, className, title, dreadcrumb }) {
  const [setTitle, setDreadcrumb] = useTitle((state) => [
    state.setTitle,
    state.setDreadcrumb,
  ]);

  useEffect(() => setTitle(title), [title, setTitle]);

  useEffect(() => setDreadcrumb(dreadcrumb), [dreadcrumb, setDreadcrumb]);

  return (
    <Fade>
      <div className={`${styles.content} ${className || ""}`}>{children}</div>
    </Fade>
  );
}

/**
 * Card组件，用于显示卡片
 *
 * @param {Object} props - 组件的props
 * @param {ReactNode} props.children - 子组件
 * @param {string} props.className - CSS类名
 * @returns {ReactNode} 返回React节点
 */
function Card({ children, className }) {
  return <div className={`card ${className || ""}`}>{children}</div>;
}

/**
 * PageTable组件，用于显示分页表格
 *
 * @param {Object} props - 组件的props
 * @param {Object} props.data - 表格数据
 * @param {boolean} props.laoding - 是否正在加载
 * @param {Function} props.onPageChange - 页码改变时的回调函数
 * @param {string} props.className - CSS类名
 * @param {ReactNode} props.children - 子组件
 * @returns {ReactNode} 返回React节点
 */
function PageTable({ data, laoding, onPageChange, className, children }) {
  return (
    <div className="table-box">
      {laoding && (
        <div className="card-loading">
          <span></span>
        </div>
      )}
      <div className="overflow-auto flex-1">
        <Table
          data={data?.records || []}
          className={`table ${className || ""}`}
        >
          {children}
        </Table>
      </div>
      <Paginate
        total={data?.total}
        size={data?.size}
        onPageChange={onPageChange}
        className="paginate"
      />
    </div>
  );
}

Content.PageTable = PageTable;
Content.Card = Card;

export default Content;
