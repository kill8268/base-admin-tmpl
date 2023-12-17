import { lazy, Suspense } from "react";
const modules = import.meta.glob("../pages/**/*.jsx");

export default function LazyHoc(path) {
  const WrappedComponent = lazy(modules[`../pages${path}`]);
  return function LazyComponent(props) {
    return (
      <Suspense>
        <WrappedComponent {...props}>{props.children}</WrappedComponent>
      </Suspense>
    );
  };
}
