import { useEffect, useState } from "react";
import { CSSTransition } from "react-transition-group";

export default function Fade({ children }) {
  const [isIn, setIn] = useState(false);
  useEffect(() => {
    setIn(true);
  }, []);
  return (
    <CSSTransition in={isIn} timeout={500} classNames="fade" unmountOnExit>
      {children}
    </CSSTransition>
  );
}
