import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import lazy from "@/helper/lazy.hoc";
import "./index.less";

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
  <RouterProvider router={createBrowserRouter(__routes.map(createRouter))} />,
);
