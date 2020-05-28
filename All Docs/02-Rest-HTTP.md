
## REST

Represeentational State Tranfer: 
+ É um deseign de arquitetura.  Cada recurso é cedido atraves de uma URL específica
+ FUnciona no modelo REQUEST (req) -> RESPONSE (res) onde há troca de mensagesn e na res há um código de status 
+ Statelss(ele nâo guarda estado, cada reuisiçâo é tratada como completamente distaitnas uma das outras. Nâo guarda nenhuma informaçâo sobre o cliente


## Verbos HTTP

POST: (Cria recurso)
+ Cria recurso Novo
+ Resposta de Sucesso: 201 (CREATED)

GET: (Obter recurso)
+ Pode envicar paramtetros para seresm critérios de busca
+ Resposa de Sucesso: 200 (OK)
+ Resposta de erro: 404 (NOT FOUND) (Quando o recurso nâo foi encontrado)

PUT (Atualizar recurso)
+ ENvio o mesmo recurso mas com alguns pontos atualizados
+ Resposta de Sucesso: 200 (OK)

DELETE
+ Deleta recurso
+ Resposta de Sucesso: 204 (NO CONTENT)

## Como estruturar

+ Na URL nÂo deve ter verbos, pois os rebos sâo os métodos HTTPP
+ Cada recurso tem um ID que é usado para identificalo

EX:

POST	/clientes	cria
GET	/clientes/1	recupera
DELETE	/clientes/1	deletea
PUT	/clientes/1	atualiza

Acessar subrecursoso

/cliente/1/enderecos/1
Acesso o endereço de ID 1 do cliente 1

Código de Status

| Verbo  | Sucesso          | Falha             |
| ------ | ---------------- | ----------------- |
| POST   | 201 (CREATED)    | 400 (BAD CONTENT) |
| GET    | 200 (OK)         | 404 (NOT FOUND)   |
| DELETE | 204 (NO CONTENT) | 400 / 404         |
| PUT    | 200 (OK) / 201   | 400 / 404         |


200 Ok: Para GET/PUT e outro HTTP métodos menores, quer dizer que deu tudo bem
201 Created: POST Criou algo no banco
204 No Content: Para Delete, quer dizer que não volta nada
400 Bad Request: E, geral, sintax mal formada, coisas de validação
401 Unauthorized : Falha na autenticação (ex: sem token)
403 Forbidden : Tem autenticaçâo masnâo para esse erviço em especicfico (ex: admin)
404 Not Found: Em geral para Get. O recurso em especifico nâo foi encontrado
409 Conflict: Erro de duplicata ou conflito de dados no banco
500 Internal Server Error: QUalquer outro eror bizzarro nâo mapeado

## OBS IMPORTANTES

**TODAS AS ENTIDADE SÂO ESCRITA EM PLURAL, nâo é `/book` é `/books`**

**É bom nâo deixar a entidade de cara, e sim colocar algo na frente como `/v1/books` ou `/api/v1/books` pra ja pensar no futuro**
