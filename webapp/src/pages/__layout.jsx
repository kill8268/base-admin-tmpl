import Menu from "@/components/menu";
import Topbar from "@/components/topbar";
import useUserStore from "@/store/useUser.store";
import { useEffect } from "react";
import { Outlet } from "react-router-dom";

export default function Layout() {
  const [initUser, initing] = useUserStore((state) => [
    state.initUser,
    state.initing,
  ]);

  useEffect(() => {
    initUser();
  }, [initUser]);

  return (
    !initing && (
      <div className="w-screen h-screen flex bg-[#E0E3EB] overflow-auto">
        <Menu />
        <div className="flex-1 flex flex-col w-0">
          <Topbar />
          <Outlet />
        </div>
      </div>
    )
  );
}
