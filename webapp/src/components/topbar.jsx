import { IoMdMoon } from "react-icons/io";
import { RiMenuUnfoldFill, RiMenuFoldFill } from "react-icons/ri";
import { MdSettings, MdOutlinePowerSettingsNew } from "react-icons/md";
import { Breadcrumb, BreadcrumbItem, BreadcrumbLink } from "@chakra-ui/react";
import useMenu from "@/store/useMenu";
import useTitle from "@/store/useTitle";

export default function Topbar() {
  const [isSmall, smallToggle] = useMenu((state) => [
    state.isSmall,
    state.smallToggle,
  ]);

  const [title, dreadcrumb] = useTitle((state) => [
    state.title,
    state.dreadcrumb,
  ]);

  return (
    <div className="h-16 px-4 py-1 mb-2 flex justify-between">
      <div>
        <Breadcrumb className="text-info">
          {dreadcrumb &&
            dreadcrumb.map(({ name, href }) => (
              <BreadcrumbItem key={name}>
                <BreadcrumbLink href={href}>{name}</BreadcrumbLink>
              </BreadcrumbItem>
            ))}
        </Breadcrumb>
        <p className="text-2xl font-semibold mt-0.5">{title}</p>
      </div>
      <div className="bg-white flex items-center justify-between shadow-lg rounded-full w-52 mt-1 h-full p-2">
        <div className="rounded-full flex items-center font-semibold justify-center h-full w-10 text-white bg-primary">
          王
        </div>
        <button onClick={smallToggle}>
          {isSmall ? (
            <RiMenuUnfoldFill size={25} className="text-info" />
          ) : (
            <RiMenuFoldFill size={25} className="text-info" />
          )}
        </button>
        <button>
          <IoMdMoon size={25} className="text-info" />
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
