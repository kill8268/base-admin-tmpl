import { useEffect } from "react";
import useUserStore from "@/store/useUser.store";
import useFetchErrorEffect from "@/hooks/useFetchErrorEffect";

export default function FetchErrorBoundary(props) {
  const logout = useUserStore((state) => state.logout);
  useFetchErrorEffect((error) => {
    const { type } = error;
    if (type === "loginout") {
      logout();
      window.location = "/login";
    }
  });

  return props.children;
}
