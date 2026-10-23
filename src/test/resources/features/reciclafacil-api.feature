# language: pt

Funcionalidade: API do ReciclaFácil
  Como usuário do ReciclaFácil
  Quero interagir com as APIs da aplicação
  Para validar autenticação, descartes e agendamentos

  Cenário: Login válido retorna token
    Dado que a API está disponível
    Quando envio credenciais válidas para o login
    Então o status HTTP deve ser 200
    E o campo "token" deve estar preenchido
    E o JSON da resposta deve corresponder ao schema "login-schema.json"

  Cenário: Login inválido é recusado
    Dado que a API está disponível
    Quando envio credenciais inválidas para o login
    Então o status HTTP deve ser 401
    E o corpo da resposta deve conter o texto "Credenciais inválidas"

  Cenário: Listar pontos de coleta
    Dado que existe um ponto de coleta cadastrado
    Quando solicito a lista de pontos de coleta
    Então o status HTTP deve ser 200
    E o JSON da resposta deve corresponder ao schema "pontos-coleta-schema.json"
    E a resposta deve conter o ponto de coleta "Ponto de Teste BDD"

  Cenário: Registrar descarte com peso válido
    Dado que existe um ponto de coleta cadastrado
    Quando registro um descarte de 5.0 kg no ponto cadastrado
    Então o status HTTP deve ser 200
    E o corpo da resposta deve conter o texto "Descarte efetuado com sucesso!"

  Cenário: Rejeitar descarte com peso igual a zero
    Dado que existe um ponto de coleta cadastrado
    Quando tento registrar um descarte de 0.0 kg no ponto cadastrado
    Então o status HTTP deve ser 400
    E o JSON da resposta deve corresponder ao schema "descarte-validation-schema.json"
    E o objeto de erro deve conter a validação "pesoKg" com a mensagem "O peso do descarte deve ser maior que zero."

  Cenário: Rejeitar descarte para ponto inexistente
    Dado que existe um ponto de coleta cadastrado
    Quando registro um descarte de 5.0 kg para um ponto inexistente
    Então o status HTTP deve ser 404
    E o corpo da resposta deve conter o texto "Ponto de coleta não encontrado"

  Cenário: Atualizar status de agendamento
    Dado que a API está disponível
    Quando atualizo o agendamento 12 para o status "EM_ANDAMENTO"
    Então o status HTTP deve ser 200
    E o corpo da resposta deve conter o texto "atualizado para o status: EM_ANDAMENTO"

  Cenário: Cancelar agendamento de coleta
    Dado que a API está disponível
    Quando cancelo o agendamento 12
    Então o status HTTP deve ser 200
    E o corpo da resposta deve conter o texto "cancelado com sucesso"