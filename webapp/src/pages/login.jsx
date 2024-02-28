export default function Login() {
  return (
    <div className="flex h-screen">
      <div className="flex-grow bg-black"></div>
      <div className="flex-1 flex">
        <form className="max-w-sm m-auto space-y-4">
          <h1 className="text-2xl font-bold">登录</h1>
          <input placeholder="Basic usage" />
          <input placeholder="Basic usage" type="password" />
          <button>登录</button>
        </form>
      </div>
    </div>
  );
}
