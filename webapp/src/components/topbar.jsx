import { IoMdMoon } from "react-icons/io";
import { RiMenuUnfoldFill, RiMenuFoldFill } from "react-icons/ri";
import { MdSettings, MdOutlinePowerSettingsNew } from "react-icons/md";
import useMenu from "@/store/useMenu";
import useTitle from "@/store/useTitle";
import useUserStore from "@/store/useUser.store";
import useThemeStore from "@/store/useTheme.store";

export default function Topbar() {
  const [isSmall, smallToggle] = useMenu((state) => [
    state.isSmall,
    state.smallToggle,
  ]);

  const [title, dreadcrumb] = useTitle((state) => [
    state.title,
    state.dreadcrumb,
  ]);

  const [theme, themeToggle] = useThemeStore((state) => [
    state.theme,
    state.toggle,
  ]);

  const info = useUserStore((state) => state.userInfo);

  return (
    <div className="h-16 py-1 flex justify-between mt-4 ml-2 mr-4">
      <div>
        <div className="breadcrumbs text-sm text-info pt-0 pb-1">
          <ul>
            {dreadcrumb &&
              dreadcrumb.map(({ name, href }) => (
                <li key={name}>
                  <a href={href}>{name}</a>
                </li>
              ))}
          </ul>
        </div>
        <p className="text-lg font-semibold">{title}</p>
      </div>
      <div className="bg-base-100 flex items-center justify-between shadow-lg rounded-full w-56 h-12 p-2 px-4">
        <div className="rounded-full flex items-center font-semibold justify-center h-full w-8 text-white bg-primary">
          {info?.username.slice(0, 1)}
        </div>
        <button onClick={smallToggle}>
          {isSmall ? (
            <RiMenuUnfoldFill size={25} className="text-info" />
          ) : (
            <RiMenuFoldFill size={25} className="text-info" />
          )}
        </button>
        <button onClick={themeToggle}>
          {theme === "light" ? (
            <IoMdMoon size={25} className="text-info" />
          ) : (
            <IoMdMoon size={25} className="text-warning" />
          )}
        </button>
        <button>
          <MdSettings size={25} className="text-info" />
        </button>
        <button>
          <MdOutlinePowerSettingsNew size={25} className="text-info" />
        </button>
      </div>
    </div>
  );
}
