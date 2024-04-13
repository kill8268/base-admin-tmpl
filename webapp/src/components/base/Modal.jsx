import ReactModal from "react-modal";
import { IoCloseSharp } from "react-icons/io5";
/**
 * 模态窗口组件。
 *
 * @component
 * @param {Object} props 属性对象。
 * @param {React.ReactNode} props.children 模态窗口的内容。
 * @param {boolean} props.isOpen 模态窗口是否打开。
 * @param {string} props.title 模态窗口的标题。
 * @param {React.ReactNode} props.footer 模态窗口的页脚。
 * @param {React.ReactNode} props.header 模态窗口的页眉。
 * @param {Function} props.onClose 当请求关闭模态窗口时调用的函数。
 * @param {string} props.className 应用于模态窗口主体的 CSS 类。
 * @returns {ReactElement} 渲染的模态窗口组件。
 */
export default function Modal({
  children,
  isOpen,
  title,
  footer,
  header,
  onClose,
  className,
}) {
  return (
    <ReactModal
      isOpen={isOpen}
      onRequestClose={onClose}
      overlayClassName="modal-overlay"
      className={className || "modal-body"}
    >
      {header || (
        <div className="flex justify-between items-center">
          <h3 className="font-bold text-xl">{title}</h3>
          <button>
            <IoCloseSharp className="text-2xl" onClick={onClose} />
          </button>
        </div>
      )}
      <div className="flex-1">{children}</div>
      {footer}
    </ReactModal>
  );
}
