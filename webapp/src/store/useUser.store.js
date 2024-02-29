import fetcher from "@/lib/fetcher";
import storage from "@/lib/storage";
import { create } from "zustand";

export default create((set) => ({
  userInfo: null,
  initing: true,
  async login(body) {
    const { token } = await fetcher(
      "POST",
      "/base-admin-server/auth/sign-in",
      body,
    );
    storage().set(storage.KEY.TOKEN, token);
    set({ token });
  },
  async initUser() {
    const res = await fetcher("GET", "/base-admin-server/auth/info");
    set({ userInfo: res, initing: false });
    storage("object").set(storage.KEY.USER_INFO, res);
  },
}));
