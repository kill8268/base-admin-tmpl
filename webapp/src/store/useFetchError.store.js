// ! 非zustand, 使用 useSyncExternalStore, 可在React组件之外的地方使用
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

function useFetchErrorStore() {
  const _error = useSyncExternalStore(subscribe, getSnapshot);
  return _error;
}

useFetchErrorStore.setError = setError;
useFetchErrorStore.clearError = clearError;

export default useFetchErrorStore;
