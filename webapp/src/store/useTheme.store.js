import { create } from "zustand";

export default create((set, get) => ({
  theme: "light",
  toggle() {
    document.body.attributes["data-theme"] =
      get().theme === "dark" ? "light" : "dark";
    set({ theme: get().theme === "dark" ? "light" : "dark" });
  },
}));
