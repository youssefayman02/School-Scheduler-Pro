package com.project.schoolschedulingsystem.Slot;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/slots")
@RestController
@AllArgsConstructor
public class SlotController {

    private final SlotService slotService;

    @GetMapping("all")
    public ResponseEntity<List<Slot>> getAllSlots() {
        return new ResponseEntity<>(slotService.getAllSlots(), HttpStatus.OK);
    }

    @GetMapping(path = "{slotId}")
    public ResponseEntity<Slot> getSlot(@PathVariable("slotId") Long id) {
        return new ResponseEntity<>(slotService.getSlot(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Slot> saveSlot(@Valid @RequestBody SlotRequestDTO slotRequestDTO)
    {
        return new ResponseEntity<>(slotService.saveSlot(slotRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{slotId}")
    public ResponseEntity<Slot> deleteSlot(@PathVariable("slotId") Long id)
    {
        return new ResponseEntity<>(slotService.deleteSlot(id), HttpStatus.OK);
    }
}
