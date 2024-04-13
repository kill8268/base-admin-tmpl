import { SWRConfig } from "swr";
import ReactModal from "react-modal";
import fetcher from "./lib/fetcher";
import lazy from "@/helper/lazy.hoc";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import FetchErrorBoundary from "./components/boundary/fetchError.boundary";
import "@/assets/styles/global.less";
ReactModal.setAppElement("#root");

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
    <FetchErrorBoundary>
      <RouterProvider
        router={createBrowserRouter(__routes.map(createRouter))}
      />
    </FetchErrorBoundary>
  </SWRConfig>,
);
