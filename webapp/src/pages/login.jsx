import { Input } from "@chakra-ui/react";
import { Button } from "@chakra-ui/react";

export default function Login() {
  return (
    <div className="flex h-screen">
      <div className="flex-grow bg-black"></div>
      <div className="flex-1 flex">
        <form className="max-w-sm m-auto space-y-4">
          <h1 className="text-2xl font-bold">登录</h1>
          <Input placeholder="Basic usage" />
          <Input placeholder="Basic usage" type="password" />
          <Button colorScheme="teal" size="md">
            登录
          </Button>
        </form>
      </div>
    </div>
  );
}
