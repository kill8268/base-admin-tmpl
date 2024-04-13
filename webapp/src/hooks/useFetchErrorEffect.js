import { useSyncExternalStore } from "react";

let error = null;
let listeners = [];

function emitChange() {
  for (const listener of listeners) {
    listener();
  }
}

function setError(code, message, type) {
  if (type === error?.type) return;
  error = { code, message, type };
  emitChange();
}

function clearError() {
  error = null;
  emitChange();
}

function subscribe(listener) {
  listeners = [...listeners, listener];
  return () => {
    listeners = listeners.filter((l) => l !== listener);
  };
}

function getSnapshot() {
  return error;
}

function useFetchErrorEffect(func) {
  const _error = useSyncExternalStore(subscribe, getSnapshot);
  if (_error) {
    func(_error);
    clearError();
  }
}

useFetchErrorEffect.setError = setError;
useFetchErrorEffect.clearError = clearError;

export default useFetchErrorEffect;
