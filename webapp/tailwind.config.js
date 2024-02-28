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
          primary: "#3F2AE7",
          "primary-focus": "#3218b2",
          "primary-content": "#ffffff",
          "success-content": "#ffffff",
          "--rounded-btn": "0.375rem",
        },
      },
    ],
  },
};
