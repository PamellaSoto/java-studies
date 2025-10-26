import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const LoginForm = () => {
  const [disabledBtn, setDisabledBtn] = useState(false);
  const navigateTo = useNavigate();

  const schema = yup.object().shape({
    loginUsername: yup.string().required("* Please, insert your username."),
    loginPassword: yup.string().required("* Please, insert your password.")
  });

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const handleLogin = async (data) => {
    try {
      const { data: token } = await api.post("/login", {
        username: data.loginUsername,
        password: data.loginPassword,
      });
      localStorage.setItem("token", token);
      setDisabledBtn(true);
      setInterval(() => {
        navigateTo("/dashboard"); 
      }, 3000);
    } catch (err) {
      console.error("Login failed", err);
    }
  };  

  return (
    <form 
      onSubmit={handleSubmit(handleLogin)}
      className="flex flex-col"
    >
      <label className="mt-6 mb-1 text-sm font-semibold" htmlFor="loginUsername">Username</label>
      <input
        className="min-w-64 border-black border rounded-md px-2 py-1 focus-within:outline-offset-4"
        type="text"
        id="loginUsername"
        placeholder="Username"
        {...register("loginUsername")}
        />
      {errors.loginUsername && <p className="text-xs mt-2 text-red-600 font-bold">{errors.loginUsername?.message}</p>}
      <label className="mt-6 mb-1 text-sm font-semibold" htmlFor="loginPassword">Password</label>
      <input
        className="min-w-64 border-black border-1 rounded-md px-2 py-1 focus-within:outline-offset-4"
        type="password"
        id="loginPassword"
        placeholder="Password"
        {...register("loginPassword")}
      />
      {errors.loginPassword && <p className="text-xs mt-2 text-red-600 font-bold">{errors.loginPassword?.message}</p>}

      <input
        className="cursor-pointer mt-6 rounded-md bg-amber-300 p-2 text-xl font-bold focus-within:outline-offset-4 disabled:bg-amber-300/50 disabled:text-gray-500"
        type="submit"
        disabled={disabledBtn}
        value={disabledBtn ? "Loading..." : "Login"}
      />
    </form>
  );
};

export default LoginForm;
