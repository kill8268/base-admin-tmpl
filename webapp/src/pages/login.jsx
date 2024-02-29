import wavy from "@/assets/wavy.svg";
import useUserStore from "@/store/useUser.store";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const login = useUserStore((state) => state.login);

  const onSubmit = async (data) => {
    await login(data);
    navigate("/");
  };

  return (
    <div className="flex h-screen">
      <div className="flex-grow bg-black"></div>
      <div className="flex-1 flex" style={{ background: `url(${wavy})` }}>
        <form
          onSubmit={handleSubmit(onSubmit)}
          className="max-w-xs m-auto space-y-2"
        >
          <h1 className="text-2xl font-bold">登录</h1>
          <div>
            <input
              className="input input-bordered focus:input-primary w-full"
              {...register("auth", { required: true })}
              placeholder="用户名/手机号"
            />
            <span className={errors.auth ? "opacity-100" : "opacity-0"}>
              This field is required
            </span>
            <input
              className="input input-bordered focus:input-primary w-full"
              {...register("password", { required: true })}
              placeholder="密码"
              type="password"
            />
            <span className={errors.password ? "opacity-100" : "opacity-0"}>
              This field is required
            </span>
          </div>
          <button className="btn btn-primary w-full" type="submit">
            登录
          </button>
        </form>
      </div>
    </div>
  );
}
