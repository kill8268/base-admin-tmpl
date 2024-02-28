import fetcher from "@/lib/fetcher";

export default function Login() {
  const handleSubmit = async () => {
    const res = await fetcher("POST", "/base-admin-server/auth/sign-in", {
      auth: "admin",
      password: "123456",
    });
    const data = await res.json();
    console.log(data);
  };

  return (
    <div className="flex h-screen">
      <div className="flex-grow bg-black"></div>
      <div className="flex-1 flex">
        <form className="max-w-sm m-auto space-y-4">
          <h1 className="text-2xl font-bold">登录</h1>
          <input className="input" placeholder="Basic usage" />
          <input className="input" placeholder="Basic usage" type="password" />
          <button onClick={handleSubmit} type="button">
            登录
          </button>
        </form>
      </div>
    </div>
  );
}
