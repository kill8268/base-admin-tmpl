import { SWRConfig } from "swr";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import lazy from "@/helper/lazy.hoc";
import fetcher from "./lib/fetcher";
import "@/assets/styles/global.less";

const createRouter = (router) => {
  const { path, element } = router;
  const Element = element && lazy(element);
  return {
    path,
    element: element && <Element />,
    children: router.children?.map((child) => createRouter(child)),
  };
};

ReactDOM.createRoot(document.getElementById("root")).render(
  <SWRConfig
    value={{
      revalidateOnFocus: false, // 禁止窗口聚焦时重新获取数据
      revalidateOnReconnect: false, // 禁止网络重新连接时重新获取数据
      shouldRetryOnError: false, // 禁止错误重试
      fetcher: ([...args]) => fetcher(...args),
    }}
  >
    <RouterProvider router={createBrowserRouter(__routes.map(createRouter))} />
  </SWRConfig>,
);
