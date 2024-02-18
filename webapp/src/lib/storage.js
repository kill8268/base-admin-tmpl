/**
 * 创建一个用于操作 localStorage 的对象。
 * @param {string} [type="string"] - 存储的数据类型，可以是 "string" 或 "object"。
 * @returns {Object} 返回一个包含 get、set、remove 和 clear 方法的对象。
 */
function storage(type = "string") {
  return {
    /**
     * 从 localStorage 中获取指定 key 的值。
     * @param {string} key - 要获取的数据的 key。
     * @throws {Error} 如果 key 为空，将抛出错误。
     * @returns {string|Object|null} 返回获取到的值。如果 type 为 "object"，将尝试解析为 JavaScript 对象。如果解析失败，返回 null。
     */
    get(key) {
      if (!key) {
        throw new Error("key is required");
      }
      let value = window.localStorage.getItem(key);
      if (type === "object") {
        try {
          value = JSON.parse(value);
        } catch (e) {
          value = null;
        }
      }
      return value;
    },
    /**
     * 将指定的 key-value 对存储到 localStorage 中。
     * @param {string} key - 要存储的数据的 key。
     * @param {string|Object} value - 要存储的值。如果 type 为 "object"，将转换为 JSON 字符串存储。
     * @throws {Error} 如果 key 为空，将抛出错误。
     */
    set(key, value) {
      if (!key) {
        throw new Error("key is required");
      }
      if (type === "object") {
        value = JSON.stringify(value);
      }
      window.localStorage.setItem(key, value);
    },
    /**
     * 从 localStorage 中删除指定 key 的数据。
     * @param {string} key - 要删除的数据的 key。
     * @throws {Error} 如果 key 为空，将抛出错误。
     */
    remove(key) {
      if (!key) {
        throw new Error("key is required");
      }
      window.localStorage.removeItem(key);
    },
    /**
     * 清空 localStorage。
     */
    clear() {
      window.localStorage.clear();
    },
  };
}

/**
 * 预定义的 key。
 * @type {Object}
 */
storage.KEY = {
  TOKEN: "jt",
};

export default storage;
