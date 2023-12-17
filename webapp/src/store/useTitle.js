import { create } from "zustand";

export default create((set) => ({
  title: "",
  dreadcrumb: [],
  setTitle(title) {
    set({ title });
  },
  setDreadcrumb(dreadcrumb) {
    set({ dreadcrumb });
  },
}));
