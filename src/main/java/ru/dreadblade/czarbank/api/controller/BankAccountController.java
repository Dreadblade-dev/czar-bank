package ru.dreadblade.czarbank.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.dreadblade.czarbank.api.mapper.BankAccountMapper;
import ru.dreadblade.czarbank.api.model.request.BankAccountRequestDTO;
import ru.dreadblade.czarbank.api.model.request.validation.CreateRequest;
import ru.dreadblade.czarbank.api.model.response.BankAccountResponseDTO;
import ru.dreadblade.czarbank.domain.BankAccount;
import ru.dreadblade.czarbank.domain.security.User;
import ru.dreadblade.czarbank.service.BankAccountService;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/bank-accounts")
@RestController
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @PreAuthorize("hasAuthority('BANK_ACCOUNT_READ') or isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<BankAccountResponseDTO>> findAllForUser(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(bankAccountService.findAllForUser(currentUser).stream()
                .map(bankAccountMapper::entityToResponseDto)
                .collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('BANK_ACCOUNT_READ') or (isAuthenticated() and @bankAccountService.findById(#accountId).getOwner().getId() == principal.id)")
    @GetMapping("/{accountId}")
    public ResponseEntity<BankAccountResponseDTO> findById(@PathVariable Long accountId) {
        BankAccount bankAccount = bankAccountService.findById(accountId);

        BankAccountResponseDTO responseDTO = bankAccountMapper.entityToResponseDto(bankAccount);
        return ResponseEntity.ok(responseDTO);
    }

    @PreAuthorize("hasAuthority('BANK_ACCOUNT_CREATE') or (isAuthenticated() and #currentUser.id == #requestDTO.ownerId)")
    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createAccount(@AuthenticationPrincipal User currentUser,
                                                                @Validated(CreateRequest.class) @RequestBody BankAccountRequestDTO requestDTO,
                                                                HttpServletRequest request) {
        Long ownerId = requestDTO.getOwnerId();
        Long bankAccountTypeId = requestDTO.getBankAccountTypeId();
        Long usedCurrencyId = requestDTO.getUsedCurrencyId();

        BankAccount createdAccount = bankAccountService.create(ownerId, bankAccountTypeId, usedCurrencyId);
        BankAccountResponseDTO responseDTO = bankAccountMapper.entityToResponseDto(createdAccount);

        return ResponseEntity.created(URI.create(request.getRequestURI() + "/" + createdAccount.getId()))
                .body(responseDTO);
    }

    @PreAuthorize("hasAuthority('BANK_ACCOUNT_DELETE') or (isAuthenticated() and @bankAccountService.findById(#accountId).getOwner().getId() == principal.id)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{accountId}")
    public void deleteAccountById(@PathVariable Long accountId) {
        bankAccountService.deleteById(accountId);
    }
}