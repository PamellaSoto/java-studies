import { Link } from 'react-router-dom';
import SignupForm from '../components/SignupForm';

const LoginPage = () => {
  return (
    <main className='bg-amber-100 min-h-dvh flex items-center justify-center'>
      <div className='bg-white rounded-xl p-10'>
        <h1 className='font-bold text-2xl text-center'>Create your account</h1>
        <SignupForm />
        <p className='mt-2 text-sm text-center'>Already have an account? <Link className='underline' to="/">Log in</Link></p>
      </div>
    </main>
  )
}

export default LoginPage