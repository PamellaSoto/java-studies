import { Link } from 'react-router-dom';
import LoginForm from '../components/LoginForm';

const LoginPage = () => {
  return (
    <main className='bg-amber-100 min-h-dvh flex items-center justify-center'>
      <div className='bg-white rounded-xl p-10'>
        <h1 className='font-bold text-2xl text-center'>Welcome!</h1>
        <LoginForm />
        <p className='mt-2 text-sm text-center'>New here? <Link className='underline' to="/signup">Create an account</Link></p>
      </div>
    </main>
  )
}

export default LoginPage