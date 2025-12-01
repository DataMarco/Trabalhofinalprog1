# Trabalhofinalprog1

## Introdução
O **KioskApp** é um sistema de autoatendimento desenvolvido em **Java** com **JavaFX**.  
Ele simula o funcionamento de quiosques de restaurantes e lancherias, permitindo que clientes realizem pedidos e que administradores acompanhem e atualizem o status dos atendimentos.

## Objetivos
- Aplicar conceitos de **orientação a objetos**: encapsulamento, herança, polimorfismo, classes abstratas, interfaces e coleções.
- Criar uma aplicação modularizada com separação entre **modelo**, **controlador** e **interface**.
- Implementar funcionalidades de **cliente** e **administrador**.

## Estrutura do Projeto
- **Model**: classes de domínio como `Produto`, `ItemPedido`, `PedidoRestaurante`, `Atendimento`.
- **Controller**: lógica da interface gráfica (`StartController`, `MainController`, `AdminController`, `CartController`).
- **View**: telas em FXML (`StartView`, `MainView`, `AdminView`, `CartView`).

## Funcionalidades
### Cliente
- Visualizar produtos.
- Adicionar e remover itens.
- Visualizar resumo do pedido.
- Confirmar pedido.
- Voltar para tela inicial.

### Administrador
- Visualizar lista de atendimentos.
- Atualizar status dos pedidos.
- Recarregar lista.
- Voltar para tela inicial.

## Decisões de Modelagem
- Uso de `ObservableList` para sincronização automática entre dados e interface.
- Separação clara entre pacotes: `model`, `controller`, `view`.
- Geração de número único de atendimento com `NumeroAtendimentoGenerator`.

## Execução
1. Clone o repositório.
2. Compile o projeto com Maven.
3. Execute com o plugin JavaFX:
   ```bash
   mvn javafx:run

