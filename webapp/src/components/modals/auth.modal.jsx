import { forwardRef, useImperativeHandle, useState } from "react";
import Modal from "../base/Modal";
import { useForm } from "react-hook-form";

const AuthModal = forwardRef((_, ref) => {
  const [id, setId] = useState(null);
  const [title, setTitle] = useState("");
  const [isOpen, setIsOpen] = useState(false);
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm();

  useImperativeHandle(ref, () => ({
    open: (id) => {
      setId(id);
      setTitle(id ? "编辑用户" : "新增用户");
      setIsOpen(true);
    },
    close: () => setIsOpen(false),
  }));

  const onSubmit = async (data) => {};

  return (
    <Modal title={title} isOpen={isOpen} onClose={() => setIsOpen(false)}>
      <form onSubmit={handleSubmit(onSubmit)}>
        <input
          className="input input-bordered focus:input-primary w-full"
          {...register("username")}
          placeholder="用户名"
        />
        <span className={errors.auth ? "opacity-100 text-error" : "opacity-0"}>
          {errors.auth?.message || "-"}
        </span>
        <input
          className="input input-bordered focus:input-primary w-full"
          placeholder="手机号"
          {...register("phone", {
            required: true,
            pattern: /^1[3-9]\d{9}$/,
            message: "请输入正确的手机号",
          })}
        />
        <span className={errors.phone ? "opacity-100 text-error" : "opacity-0"}>
          {errors.phone?.message || "-"}
        </span>
        <input
          className="select select-bordered focus:select-primary w-full"
          {...register("roleId")}
          placeholder="角色"
        />
        <span className={errors.auth ? "opacity-100 text-error" : "opacity-0"}>
          {errors.auth?.message || "-"}
        </span>
        <div>
          <button className="btn btn-primary w-full" type="submit">
            确定
          </button>
        </div>
      </form>
    </Modal>
  );
});

AuthModal.displayName = "AuthModal";

export default AuthModal;
