import daisyui from "daisyui";
import themes from "daisyui/src/theming/themes";

/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{jsx,html}"],
  plugins: [daisyui],
  daisyui: {
    themes: [
      {
        light: {
          ...themes["light"],
          primary: "#319795",
          info: "#6B7280",
          "info-focus": "#4B5563",
          "info-content": "#ffffff",
          "primary-focus": "#319795",
          "primary-content": "#ffffff",
          "success-content": "#ffffff",
          "--rounded-btn": "0.375rem",
        },
        dark: {
          ...themes["dark"],
          primary: "#319795",
          info: "#6B7280",
          "info-focus": "#4B5563",
          "info-content": "#ffffff",
          "primary-focus": "#319795",
          "primary-content": "#ffffff",
          "success-content": "#ffffff",
          "--rounded-btn": "0.375rem",
        },
      },
    ],
  },
};
