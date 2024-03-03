import Content from "@/components/content";
import Paginate from "react-paginate";
import { FaPlus, FaRedo, FaSearch } from "react-icons/fa";

export default function Dict() {
  return (
    <Content
      title="系统字典"
      dreadcrumb={[{ name: "系统管理" }, { name: "字典管理" }]}
    >
      <div className="card bg-base-100 p-2 shadow-xl flex-1 overflow-auto">
        <div className="flex flex-wrap justify-between py-2 px-2 gap-4">
          <button className="btn btn-sm px-2 btn-success">
            <FaPlus />
          </button>
          <form className="flex flex-wrap justify-end gap-4">
            <button className="btn btn-sm px-2 btn-primary" type="submit">
              <FaSearch />
            </button>
            <button className="btn btn-sm px-2" type="reset">
              <FaRedo />
            </button>
          </form>
        </div>
        <div className="overflow-x-auto flex-1"></div>
        <div className="py-2 px-4 flex justify-between gap-8 font-semibold">
          <div className="space-x-2">
            <span>所有记录</span>
            <span className="bg-primary text-white h-8 inline-block font-semibold rounded-full px-3 py-1">
              123&nbsp;条
            </span>
          </div>
          <div className="flex space-x-4">
            <Paginate
              className="flex gap-4 items-center"
              breakLabel="..."
              nextLabel="下一页"
              nextClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
              onPageChange={() => {}}
              pageRangeDisplayed={3}
              pageCount={10}
              activeClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
              pageClassName="font-semibold"
              previousLabel="上一页"
              previousClassName="bg-primary text-white font-semibold rounded-full px-3 py-1"
              renderOnZeroPageCount={null}
            />
            <div className="space-x-2">
              <span>前往</span>
              <span className="bg-primary text-white h-8 inline-block font-semibold rounded-full text-center px-4 py-1">
                1
              </span>
              <span>页</span>
            </div>
          </div>
        </div>
      </div>
    </Content>
  );
}
