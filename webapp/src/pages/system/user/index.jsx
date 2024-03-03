import Content from "@/components/content";
import Table from "@/components/table";
import { useState } from "react";
import { FaPlus, FaRedo, FaSearch } from "react-icons/fa";
import useSWR from "swr";

export default function User() {
  const [param, setParam] = useState({
    current: 1,
    size: 10,
  });

  const { data, isLoading } = useSWR([
    "GET",
    "/base-admin-server/auth/page",
    param,
  ]);

  return (
    <Content
      title="系统用户"
      dreadcrumb={[{ name: "系统管理" }, { name: "用户管理" }]}
    >
      <Content.Card>
        <div className="flex flex-wrap justify-between py-3 p-4 gap-4">
          <button className="btn btn-sm px-2 btn-primary">
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
        <Content.PageTable
          data={data}
          laoding={isLoading}
          onPageChange={(current) =>
            setParam((param) => ({ ...param, current }))
          }
        >
          <Table.Column title="序号" name="id" />
          <Table.Column title="用户名" name="username" />
          <Table.Column title="电话号" name="phone" />
          <Table.Column title="角色" name="rol" />
          <Table.Column title="启用" name="enable" type="boolean" />
          <Table.Column title="创建人" name="createdBy" />
          <Table.Column title="创建时间" name="createdAt" type="datetime" />
          <Table.Column title="修改人" name="updatedBy" />
          <Table.Column title="修改时间" name="updatedAt" type="datetime" />
          <Table.Column
            title="操作"
            name="edit"
            render={() => (
              <button className="text-primary font-semibold">编辑</button>
            )}
          />
        </Content.PageTable>
      </Content.Card>
    </Content>
  );
}
