import { useEffect } from "react";
import Menu from "@/components/menu";
import Topbar from "@/components/topbar";
import { Outlet } from "react-router-dom";
import useUserStore from "@/store/useUser.store";
import { CSSTransition } from "react-transition-group";

export default function Layout() {
  const [initUser, initing] = useUserStore((state) => [
    state.initUser,
    state.initing,
  ]);

  useEffect(() => {
    initUser();
  }, [initUser]);

  return (
    <CSSTransition in={!initing} timeout={500} classNames="fade" unmountOnExit>
      <div className="w-screen h-screen flex overflow-auto">
        <Menu />
        <div className="flex-1 flex flex-col w-0">
          <Topbar />
          <Outlet />
        </div>
      </div>
    </CSSTransition>
  );
}
