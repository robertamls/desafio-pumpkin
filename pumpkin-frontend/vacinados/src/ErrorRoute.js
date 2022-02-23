import React from 'react'
import notfound from './assets/notfound.jpg'
import './style.css'

function ErrorRoute() {
    return (
        <div className="container">
            <img className="img-notFound" src={notfound} alt="404" />
        </div>
    )
}

export default ErrorRoute;