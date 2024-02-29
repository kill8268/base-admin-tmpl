import useFetchErrorStore from "@/store/useFetchError.store";
import storage from "./storage";

const URL = (url, params) => {
  if (!params) {
    return url;
  }
  const paramsArray = [];
  Object.keys(params)
    .filter((key) => params[key])
    .forEach((key) => paramsArray.push(`${key}=${params[key]}`));
  return `${url}?${paramsArray.join("&")}`;
};

/**
 * @description http 请求方法
 * @throws http 错误&后端异常将在 FetchErrorBoundary 处理，无需在页面中处理
 * @async
 * @param {'GET'|'POST'| 'PUT'| 'DELETE'} method
 * @param {string} url
 * @param {object} data
 * @returns {Promise<any>}
 */
export default async function fetcher(method, url, data, timeout = 5000) {
  if (!url) {
    throw new Error("url is required");
  }

  if (!method) {
    throw new Error("method is required");
  }

  const headers = {
    "Content-Type": "application/json",
    Authorization: `${storage().get(storage.KEY.TOKEN)}`,
    Device: "PC",
    DeviceId: "PC",
  };

  const fetchPromise = (async () => {
    switch (method) {
      case "POST":
      case "PUT":
      case "PATCH":
        return await window.fetch(URL(url), {
          method,
          headers,
          body: JSON.stringify(data),
        });
      case "GET":
      case "DELETE":
      default:
        return await window.fetch(URL(url, data), {
          method: method || "GET",
          headers,
        });
    }
  })();

  const timeoutPromise = new Promise((_, reject) =>
    setTimeout(() => {
      useFetchErrorStore.setError(res.status, "网路异常，请稍后再试", "http");
      reject(new Error("[network]:Request timed out"));
    }, timeout),
  );

  const res = await Promise.race([fetchPromise, timeoutPromise]);
  if (res.status === 401) {
    const response = await res.json();
    const { message } = response;
    useFetchErrorStore.setError(res.status, message, "loginout");
    throw new Error(`[api][${res.status}]:登录状态变更`);
  }
  if (res.status >= 400) {
    const response = await res.json();
    const { message } = response;
    useFetchErrorStore.setError(res.status, message, "http");
    throw new Error(`[api][${res.status}]:请求异常`);
  }
  const response = await res.json();
  return response;
}
