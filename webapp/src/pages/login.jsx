import { useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import useUserStore from "@/store/useUser.store";
import background from "@/assets/login-background.webp";
import Fade from "@/components/fade";

export default function Login() {
  const navigate = useNavigate();
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm();

  const login = useUserStore((state) => state.login);

  const onSubmit = async (data) => {
    try {
      await login(data);
      navigate("/");
    } catch (error) {
      console.log(error);
      setError("auth", { message: "用户名或密码错误" });
    }
  };

  return (
    <div className="w-screen h-screen flex">
      <Fade>
        <div className="flex m-auto bg-white min-w-[1200px] min-h-[675px] w-[50%] h-[50%] rounded-lg overflow-hidden shadow">
          <div className="flex-[2] relative">
            <div className="skew-x-6 w-full absolute right-[10%] h-full overflow-hidden">
              <img
                className="block -skew-x-6 w-full relative h-full left-[10%]"
                src={background}
              />
            </div>
          </div>
          <div className="flex-1 flex">
            <form onSubmit={handleSubmit(onSubmit)} className="max-w-xs m-auto">
              <h1 className="text-2xl mb-4 font-bold text-primary">欢迎!</h1>
              <div>
                <input
                  className="input input-bordered focus:input-primary w-full"
                  {...register("auth")}
                  placeholder="用户名/手机号"
                />
                <span
                  className={
                    errors.auth ? "opacity-100 text-error" : "opacity-0"
                  }
                >
                  {errors.auth?.message || "-"}
                </span>
                <input
                  className="input input-bordered focus:input-primary w-full"
                  {...register("password")}
                  placeholder="密码"
                  type="password"
                />
                <span
                  className={
                    errors.password ? "opacity-100 text-error" : "opacity-0"
                  }
                >
                  {errors.password?.message || "-"}
                </span>
              </div>
              <button className="btn btn-primary w-full" type="submit">
                登录
              </button>
            </form>
          </div>
        </div>
      </Fade>
    </div>
  );
}
