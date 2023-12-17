import { create } from "zustand";

export default create((set, get) => ({
  menu: [],
  isSmall: false,
  smallToggle() {
    set({ isSmall: !get().isSmall });
  },
}));
