import useTitle from "@/store/useTitle";
import { Fade } from "@chakra-ui/transition";
import { useEffect } from "react";

export default function Content({ children, className, title, dreadcrumb }) {
  const [setTitle, setDreadcrumb] = useTitle((state) => [
    state.setTitle,
    state.setDreadcrumb,
  ]);

  useEffect(() => setTitle(title), [title, setTitle]);

  useEffect(() => setDreadcrumb(dreadcrumb), [dreadcrumb, setDreadcrumb]);

  return (
    <Fade
      className={`flex-1 flex flex-col pb-4 px-4 overflow-auto ${
        className || ""
      }`}
      in={true}
      timeout={500}
    >
      {children}
    </Fade>
  );
}
