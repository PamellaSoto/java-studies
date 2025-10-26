import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

const LoginForm = () => {
  const [disabledBtn, setDisabledBtn] = useState(false);
  const [responseAPI, setResponseAPI] = useState("");
  const navigateTo = useNavigate();

  const schema = yup.object().shape({
    signupName: yup.string().max(50).required("* Please, insert your name."),
    signupUsername: yup.string().max(50).required("* Please, insert your username."),
    signupPassword: yup.string().min(8, '* Password must be at least 8 characters long').required("* Please, insert your password.")
  });

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const handleSignup = async (data) => {
    try {
      const response = await api.post("/users/new", {
        name: data.signupName,
        username: data.signupUsername,
        password: data.signupPassword,
      });
      setDisabledBtn(true);
      setResponseAPI("Account created! Redirecting to login page...")
      setInterval(() => {
        navigateTo("/"); 
      }, 3000);
    } catch (err) {
      console.error("Signup failed", err);
      setResponseAPI("Signup failed. Try again.");
    }
  };

  return (
    <form 
      onSubmit={handleSubmit(handleSignup)}
      className="flex flex-col"
    >
      <label className="mt-6 mb-1 text-sm font-semibold" htmlFor="signupName">Name</label>
      <input
        className="min-w-64 border-black border rounded-md px-2 py-1 focus-within:outline-offset-4"
        type="text"
        id="signupName"
        placeholder="Name"
        {...register("signupName")}
        />
      {errors.signupUsername && <p className="text-xs mt-2 text-red-600 font-bold">{errors.signupName?.message}</p>}

      <label className="mt-6 mb-1 text-sm font-semibold" htmlFor="signupUsername">Username</label>
      <input
        className="min-w-64 border-black border rounded-md px-2 py-1 focus-within:outline-offset-4"
        type="text"
        id="signupUsername"
        placeholder="Username"
        {...register("signupUsername")}
        />
      {errors.signupUsername && <p className="text-xs mt-2 text-red-600 font-bold">{errors.signupUsername?.message}</p>}
      <label className="mt-6 mb-1 text-sm font-semibold" htmlFor="signupPassword">Password</label>
      <input
        className="min-w-64 border-black border-1 rounded-md px-2 py-1 focus-within:outline-offset-4"
        type="password"
        id="signupPassword"
        placeholder="Password"
        {...register("signupPassword")}
      />
      {errors.signupPassword && <p className="text-xs mt-2 text-red-600 font-bold">{errors.signupPassword?.message}</p>}

      <input
        className="cursor-pointer mt-6 rounded-md bg-amber-300 p-2 text-xl font-bold focus-within:outline-offset-4 disabled:bg-amber-300/50 disabled:text-gray-500"
        type="submit"
        disabled={disabledBtn}
        value={disabledBtn ? "Loading..." : "Signup"}
      />
       {responseAPI && <p className="text-xs mt-2 text-green-600 font-bold">{responseAPI}</p>}
    </form>
  );
};

export default LoginForm;
