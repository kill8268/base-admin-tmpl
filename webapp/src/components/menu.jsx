import { useMemo } from "react";
import useMenu from "@/store/useMenu";
import { Link } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { MdHome, MdDonutSmall } from "react-icons/md";
import { FaUser, FaBook, FaIdCard, FaBookmark } from "react-icons/fa";
import styles from "@/assets/styles/menu.module.less";

function Item({ path, icon, title }) {
  const { pathname } = useLocation();

  const active = useMemo(() => {
    if (path === "/") {
      return pathname === path;
    }
    return pathname.includes(path);
  }, [pathname, path]);

  return (
    <Link to={path} className="block">
      <li className={`menu-nav-item  ${active ? "menu-item-active" : ""}`}>
        <i className="menu-icon">{icon}</i>
        <span>{title}</span>
      </li>
    </Link>
  );
}

export default function Menu() {
  const isSmall = useMenu((state) => state.isSmall);

  return (
    <div
      className={`${styles["page-menu"]} ${
        isSmall ? styles["page-menu-samll"] : ""
      }`}
    >
      <h1 className="title">亚斯说这里是标题</h1>
      <hr className="title-hr" />
      <ul className="menu-nav">
        <Item path="/" icon={<MdHome size={20} />} title="首页" />
        <Item path="/m1" icon={<MdDonutSmall size={20} />} title="模块1" />
        <Item path="/m2" icon={<MdDonutSmall size={20} />} title="模块2" />
        <div className="menu-nav-title">系统管理</div>
        <Item path="/system/dict" icon={<FaBook />} title="字典管理" />
        <Item path="/system/user" icon={<FaUser />} title="用户管理" />
        <Item path="/system/role" icon={<FaIdCard />} title="角色管理" />
        <Item path="/system/menu" icon={<FaBookmark />} title="菜单管理" />
      </ul>
    </div>
  );
}
