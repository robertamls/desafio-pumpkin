import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import Title from './Title';

function createData(id, nome, dataNascimento, cpf, vacinacaoEmDia) {
  return { id, nome, dataNascimento, cpf, vacinacaoEmDia };
}

const rows = [
  createData(
    1,
    'Luiz Antonio Ribeiro',
    '15/04/2012',
    '456.897.212-56',
    'Sim',
  ),
  createData(
    2,
    'Ana Clara Domingos',
    '02/03/2015',
    '125.879.358-99',
    'Não',
  ),
  createData(
    3,
    'Larissa Mendes',
    '16/02/2020',
    '755.963.978-52',
    'Sim',
  ),
  createData(
    4,
    'Alberto Teodoro',
    '25/08/2018',
    '023.654.777-59',
    'Não',
  ),
];


class TableCriancas extends React.Component {
  render() {
    return (
      <React.Fragment>
        <Title>Crianças Vacinadas</Title>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell align="left">Nome</TableCell>
              <TableCell align="left">Data de Nascimento</TableCell>
              <TableCell align="left">Cadastro de Pessoa Fisica</TableCell>
              <TableCell align="left">Vacinação em dia</TableCell>
              <TableCell align="left">Opções</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow key={row.id}>
                <TableCell align="left">{row.nome}</TableCell>
                <TableCell align="left">{row.dataNascimento}</TableCell>
                <TableCell align="left">{row.cpf}</TableCell>
                <TableCell align="left">{row.vacinacaoEmDia}</TableCell>
                <TableCell align="left">
                    <IconButton color="primary" aria-label="Editar">
                      <EditIcon />
                    </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </React.Fragment>
    )
  }
}

export default TableCriancas;