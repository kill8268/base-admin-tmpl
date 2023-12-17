import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { ChakraProvider, extendTheme, ColorModeScript } from "@chakra-ui/react";
import lazy from "@/helper/lazy.hoc";
import "./index.less";

const theme = extendTheme({
  colors: {
    primary: {
      50: "#F8F7FF",
      100: "#EDEBFF",
      200: "#D1CCFF",
      300: "#B5ADFF",
      400: "#7D7AFF",
      500: "#4537FF",
      600: "#3F2AE7",
      700: "#2A1C99",
      800: "#201566",
      900: "#161033",
    },
    info: "#848fac",
  },
  config: {
    useSystemColorMode: true,
  },
});

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
  <ChakraProvider theme={theme}>
    <ColorModeScript initialColorMode={theme.config.initialColorMode} />
    <RouterProvider router={createBrowserRouter(__routes.map(createRouter))} />
  </ChakraProvider>,
);
