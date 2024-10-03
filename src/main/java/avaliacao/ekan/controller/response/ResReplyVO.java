package avaliacao.ekan.controller.response;

/**
 * Response de "reply"<br>
 * 
 * Possiveis prefixos:
 * <ul>
 * <li>
 * ERR_ - Indica ERRO
 * </li>
 * <li>
 * RPL - Indica retorno de sucesso
 * </li>
 * </ul>
 */
public record ResReplyVO (String rpl) {}