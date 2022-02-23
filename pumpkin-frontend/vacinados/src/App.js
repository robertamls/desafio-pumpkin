import './style.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Login from './Login';
import Home from './Home';
import TableCriancas from './Criancas';
import TableVacinas from './Vacinas';
import ErrorRoute from './ErrorRoute';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Login/>}/>
        <Route exact path="/criancas" element={<TableCriancas/>}/>
        <Route exact path="/vacinas" element={<TableVacinas/>}/>
        <Route exact path="/home" element={<Home/>}/>
        <Route path="*" element={<ErrorRoute/>}/>
      </Routes>
    </Router>
  );
}

export default App;
