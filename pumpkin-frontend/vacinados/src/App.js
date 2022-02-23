/* eslint-disable jsx-a11y/anchor-is-valid */
import { useState } from 'react';

import zeGotinha from './assets/ze-gotinha.gif'

import './style.css'

function App() {
  const [user, setUser] = useState("")
  const [pass, setPass] = useState("")
  return (
    <div className="container">
      <div className="container-login">
        <div className="wrap-login">
          <form className="login-form">
            <span className="login-form-title">Vacinados</span>
            <span className="login-form-title">
            <img src={zeGotinha} alt="Zé-Gotinha"/>
            </span>

            <div className="wrap-input">
              <input className={user !== "" ?  'has-value input' : 'input'} type="user" value={user} onChange={e => setUser(e.target.value)}/>
              <span className="effect-input" data-placeholder="Usuário"></span>
            </div>

            <div className="wrap-input">
              <input className={pass !== "" ?  'has-value input' : 'input'} type="password" value={pass} onChange={e => setPass(e.target.value)}/>
              <span className="effect-input" data-placeholder="Senha"></span>
            </div>

            <div className="container-login-form-btn">
              <button className="login-form-btn">Login</button>
            </div>

            <div className="text-new-account">
              <span className="text-um">Não possui conta?</span>
              <a className="text-dois" href="#">Criar conta</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default App;
