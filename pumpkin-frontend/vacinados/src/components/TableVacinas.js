import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import IconButton from '@mui/material/IconButton';
import EditIcon from '@mui/icons-material/Edit';
import Title from './Title';

function createData(id, nome, sigla) {
  return {id, nome, sigla};
}

const rows = [
    createData(
      1,
      'Tuberculose',
      'BCG'
    ),
    createData(
      1,
      'Poliomielite',
      'VOP'
    ),
    createData(
      1,
      'Haemopilhus',
      'Tetravalente'
    ),
    createData(
      1,
      'Sarampo',
      'SRC'
    ),
    createData(
      1,
      'Hepatite B',
      ''
    ),
    createData(
      1,
      'Febre Amarela',
      ''
    ),
  ];
  


class TableVacinas extends React.Component {
  render() {
    return (
      <React.Fragment>
        <Title>Vacinas</Title>
        <Table size="small">
          <TableHead>
            <TableRow>
              <TableCell align="left">Nome</TableCell>
              <TableCell align="left">Sigla</TableCell>
              <TableCell align="left">Opções</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {rows.map((row) => (
              <TableRow key={row.id}>
                <TableCell align="left">{row.nome}</TableCell>
                <TableCell align="left">{row.sigla}</TableCell>
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

export default TableVacinas;