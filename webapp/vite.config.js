import { resolve } from "path";
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import tailwindcss from "tailwindcss";
import autoprefixer from "autoprefixer";
import AutoRouterPlugin from "./plugins/auto-router";

export default defineConfig({
  root: "src",
  plugins: [
    react(),
    AutoRouterPlugin({
      separateFile: ["login.jsx"], // 单独层级文件
    }),
  ],
  build: {
    rollupOptions: {
      input: {
        index: resolve("src/index.html"),
      },
    },
  },
  css: {
    postcss: {
      plugins: [tailwindcss, autoprefixer],
    },
  },
  resolve: {
    alias: {
      "@/": "/",
    },
  },
  server: {
    host: true,
    port: 3000,
  },
});
