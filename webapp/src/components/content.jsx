import useTitle from "@/store/useTitle";
import { useEffect } from "react";

export default function Content({ children, className, title, dreadcrumb }) {
  const [setTitle, setDreadcrumb] = useTitle((state) => [
    state.setTitle,
    state.setDreadcrumb,
  ]);

  useEffect(() => setTitle(title), [title, setTitle]);

  useEffect(() => setDreadcrumb(dreadcrumb), [dreadcrumb, setDreadcrumb]);

  return (
    <div
      className={`flex-1 flex flex-col p-4 pt-2 overflow-auto ${
        className || ""
      }`}
    >
      {children}
    </div>
  );
}
