import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import tailwindcss from "tailwindcss";
import autoprefixer from "autoprefixer";
import AutoRouterPlugin from "./plugins/auto-router";

export default defineConfig({
  plugins: [
    react(),
    AutoRouterPlugin({
      separateFile: ["login.jsx"], // 单独层级文件
    }),
  ],
  css: {
    postcss: {
      plugins: [tailwindcss, autoprefixer],
    },
  },
  resolve: {
    alias: {
      "@/": "/src/",
    },
  },
  server: {
    host: true,
    port: 3000,
    proxy: {
      "/base-admin-server": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});
