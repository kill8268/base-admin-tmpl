import fetcher from "@/lib/fetcher";
import storage from "@/lib/storage";
import { create } from "zustand";

export default create((set) => ({
  useInfo: null,
  initing: true,
  async login(body) {
    const res = await fetcher("POST", "/base-admin-server/auth/sign-in", body);
    storage().set(storage.KEY.TOKEN, res.result);
    set({ token: res.result });
  },
  async initUser() {
    // TODO: 从 服务器 获取 用户数据。
  },
}));
