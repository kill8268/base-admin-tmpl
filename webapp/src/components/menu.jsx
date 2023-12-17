import { MdHome, MdDonutSmall } from "react-icons/md";
import { FaUser, FaBook, FaIdCard, FaBookmark } from "react-icons/fa";
import useMenu from "@/store/useMenu";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useMemo } from "react";

function Item({ path, icon, title }) {
  const { pathname } = useLocation();
  const isSmall = useMenu((state) => state.isSmall);

  const active = useMemo(() => {
    if (path === "/") {
      return pathname === path;
    }
    return pathname.includes(path);
  }, [pathname, path]);

  return (
    <Link to={path}>
      <li className={`menu-item ${active ? "menu-item-active" : ""}`}>
        <i className="icon">{icon}</i>
        <span>{title}</span>
      </li>
    </Link>
  );
}

export default function Menu() {
  const isSmall = useMenu((state) => state.isSmall);

  return (
    <div className={`nav ${isSmall ? "nav-samll" : ""}`}>
      <h1 className="title">亚斯说这里是标题</h1>
      <hr className="title-hr" />
      <ul className="menu">
        <Item path="/" icon={<MdHome size={20} />} title="首页" />
        <Item path="/m1" icon={<MdDonutSmall size={20} />} title="模块1" />
        <Item path="/m2" icon={<MdDonutSmall size={20} />} title="模块2" />
        <div className="menu-title">系统管理</div>
        <Item path="/system/dict" icon={<FaBook />} title="字典管理" />
        <Item path="/system/user" icon={<FaUser />} title="用户管理" />
        <Item path="/system/role" icon={<FaIdCard />} title="角色管理" />
        <Item path="/system/menu" icon={<FaBookmark />} title="菜单管理" />
      </ul>
    </div>
  );
}
