import { BrowserRouter, Routes, Route } from "react-router-dom";


import Task from "./pages/Task";
import Index from "../public/index";
 

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Task />} />
             
                <Route path="/index" element={<Index />} />

            </Routes>
        </BrowserRouter>
    );
}

export default App;